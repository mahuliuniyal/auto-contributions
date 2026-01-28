#!/bin/bash
#
# Learning Objective:
# This script demonstrates how to visualize and navigate your Git commit history
# using Bash scripting and Graphviz. It will generate a DOT file representing
# your commit graph and then use Graphviz to render it as an image.
# This helps understand the branching and merging structure of your project.

# --- Configuration ---

# The output file name for the Graphviz DOT source.
# We'll use a .dot extension as it's standard for Graphviz.
OUTPUT_DOT_FILE="commit_history.dot"

# The desired output image format for Graphviz.
# Common options include png, svg, jpg. SVG is good for scalability.
OUTPUT_IMAGE_FORMAT="svg"

# The name of the output image file.
# We'll base it on the DOT file name and the chosen format.
OUTPUT_IMAGE_FILE="${OUTPUT_DOT_FILE%.*}.${OUTPUT_IMAGE_FORMAT}"

# --- Pre-requisite Checks ---

# Check if Graphviz is installed.
# Graphviz is essential for rendering the commit graph.
# The 'command -v' command checks if an executable is in the PATH.
if ! command -v dot &> /dev/null; then
  echo "Error: Graphviz is not installed."
  echo "Please install Graphviz. On Debian/Ubuntu: sudo apt install graphviz"
  echo "On macOS (using Homebrew): brew install graphviz"
  exit 1
fi

# Check if Git is installed.
# We need Git to extract commit history information.
if ! command -v git &> /dev/null; then
  echo "Error: Git is not installed."
  echo "Please install Git."
  exit 1
fi

# --- Core Logic: Generating the DOT file ---

# Start generating the DOT file.
# The 'echo' command writes output to stdout.
# The '>' operator redirects stdout to a file, overwriting it if it exists.
echo "digraph GitCommitHistory {" > "$OUTPUT_DOT_FILE"

# Set some graph attributes for better readability.
# 'rankdir=TB' means Top-to-Bottom layout.
# 'node [shape=box]' makes commit nodes rectangular boxes.
# 'edge [arrowhead=vee]' sets the arrow style for parent-child relationships.
echo "  rankdir=TB;" >> "$OUTPUT_DOT_FILE"
echo "  node [shape=box, style=filled, fillcolor=lightblue];" >> "$OUTPUT_DOT_FILE"
echo "  edge [arrowhead=vee];" >> "$OUTPUT_DOT_FILE"

# Fetch Git log information.
# 'git log --graph --pretty=format:"%h - %an, %ar : %s"' is a powerful command.
# --graph: Shows the commit graph structure.
# --pretty=format:"%h - %an, %ar : %s": Customizes the output format for each commit.
#   %h: abbreviated commit hash
#   %an: author name
#   %ar: author date, relative
#   %s: subject (commit message title)
# The '--all' flag includes all branches, not just the current one.
# The 'while read' loop processes each line of the git log output.
# 'IFS=' prevents whitespace splitting of lines.
# '-r' prevents backslash interpretation.
git log --graph --all --pretty=format:"%h - %an, %ar : %s" | while IFS= read -r line; do
  # Skip empty lines that might occur in the git log output.
  if [[ -z "$line" ]]; then
    continue
  fi

  # Extract the commit hash.
  # We use 'cut' to get the first field separated by " - ".
  commit_hash=$(echo "$line" | cut -d ' ' -f 1)

  # Add a node for each commit.
  # 'graphviz_label' will hold the formatted label for the commit node.
  # We need to escape special characters in the commit message for Graphviz.
  # '\' and '"' are the main ones to worry about for node labels.
  graphviz_label=$(echo "$line" | sed 's/\\/\\\\/g; s/"/\\"/g')
  echo "  \"$commit_hash\" [label=\"$graphviz_label\"];" >> "$OUTPUT_DOT_FILE"

  # Extract parent commit hashes.
  # This is the trickiest part and relies on the `--graph` output format.
  # Git log with --graph prefixes lines with characters indicating graph structure.
  # We're looking for lines that represent a commit itself and have parent connections.
  # A line starting with '|' or '*' usually indicates a commit.
  # We can parse the graph string to find parent hashes.
  # However, a simpler approach is to use `git rev-list --parents -n 1 $commit_hash`.
  # This command shows the commit hash and its parent(s).

  # Get the direct parents of the current commit.
  # `git rev-list --parents -n 1 $commit_hash` returns lines like:
  # <commit_hash> <parent1_hash> <parent2_hash> ...
  # We extract all fields *after* the first one (which is the commit_hash itself).
  parents=$(git rev-list --parents -n 1 "$commit_hash" | cut -d ' ' -f 2-)

  # If there are parents, add edges to the DOT file.
  # 'IFS=' ' ' splits the 'parents' string by spaces into an array.
  IFS=' ' read -r -a parent_array <<< "$parents"
  for parent_hash in "${parent_array[@]}"; do
    # Ensure we don't try to add an edge to an empty parent (e.g., for initial commit)
    if [[ -n "$parent_hash" ]]; then
      # Add an edge from the parent to the current commit.
      # This represents the chronological flow of commits.
      echo "  \"$parent_hash\" -> \"$commit_hash\";" >> "$OUTPUT_DOT_FILE"
    fi
  done
done

# Close the digraph definition.
echo "}" >> "$OUTPUT_DOT_FILE"

# --- Rendering the Graph ---

echo "Generated DOT file: $OUTPUT_DOT_FILE"

# Use Graphviz's 'dot' command to render the DOT file into an image.
# The '-T' option specifies the output format.
# The '-o' option specifies the output file name.
echo "Rendering commit history graph to: $OUTPUT_IMAGE_FILE"
dot -T"$OUTPUT_IMAGE_FORMAT" "$OUTPUT_DOT_FILE" -o "$OUTPUT_IMAGE_FILE"

echo "Successfully generated commit history visualization!"
echo "Open '$OUTPUT_IMAGE_FILE' in your web browser or an image viewer."

# --- Example Usage ---
#
# 1. Save this script as 'git_history_viz.sh'.
# 2. Make it executable: 'chmod +x git_history_viz.sh'
# 3. Navigate to your Git repository directory in the terminal.
# 4. Run the script: './git_history_viz.sh'
# 5. This will create 'commit_history.dot' and 'commit_history.svg' (or your chosen format).
# 6. Open the SVG file to view your commit history graph.
#
# You can customize:
# - OUTPUT_IMAGE_FORMAT to 'png', 'jpg', etc.
# - You can also modify the 'git log --pretty=format:' string for different commit details.
#
# To explore different branches, ensure your Git repository is up-to-date
# and the script uses `--all` to fetch all branches.
#
# For complex histories, the graph can become large. Graphviz's layout
# algorithm will try its best to make it readable.